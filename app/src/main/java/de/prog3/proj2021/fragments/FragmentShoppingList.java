package de.prog3.proj2021.fragments;

/**
 * UI ShoppingList Fragment of MainActivity
 * Instantiates a ViewModel to retrieve ShoppingList data from repository.
 * Initiates a RecyclerView to display the shopping list overview.
 *
 * @author Eric Walter
 * @author Giuseppe Buccellato
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.RecipeRecyclerViewAdapter;
import de.prog3.proj2021.adapters.ShoppingFragmentRecyclerViewAdapter;
import de.prog3.proj2021.db.ShoppingListWithIngredients;
import de.prog3.proj2021.db.UserWithShoppingLists;
import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.ShoppingList;
import de.prog3.proj2021.viewmodels.RecipeViewModel;
import de.prog3.proj2021.viewmodels.ShoppingListViewModel;

public class FragmentShoppingList extends Fragment {

    ShoppingFragmentRecyclerViewAdapter shoppingFragmentRecyclerViewAdapter;
    RecyclerView shoppingFragmentRecyclerView;
    ShoppingListViewModel mShoppingListViewModel;

    List<ShoppingListWithIngredients> allShoppingLists = new ArrayList<>();

    int userOwnerId = 1; //hard-coded for now, may expand user-features later

    FloatingActionButton fab;
    /**
     * onCreateView method displays the fragment
     * @param inflater layout infalter
     * @param container container that will display the fragment
     * @param savedInstanceState Bundle state
     * @return View of the fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        //instantiate RecipeRecyclerView and Adapter
        initRecyclerView(root);

        //instantiate ViewModel and Observer
        initViewModels();

        initFab(root);

        return root;
    }

    /**
     * initialise shoppingFragmentRecyclerView with Adapter
     * */
    private void initRecyclerView(View root){
        shoppingFragmentRecyclerView = root.findViewById(R.id.shoppingListOverviewRecyclerView);
        shoppingFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingFragmentRecyclerView.setHasFixedSize(true);

        shoppingFragmentRecyclerViewAdapter = new ShoppingFragmentRecyclerViewAdapter(getContext());
        shoppingFragmentRecyclerView.setAdapter(shoppingFragmentRecyclerViewAdapter);
    }

    /**
     * Observe ViewModel for changes, query ShoppingLists from
     * ShoppingListRepository and pass data to RecyclerViewAdapter
     */
    private void initViewModels(){
        mShoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);

        mShoppingListViewModel.getMShoppingListsWithIngredients().observe(getViewLifecycleOwner(), shoppingLists -> { //Observable lambda expression
            setCurrentShoppingLists(shoppingLists);
            shoppingFragmentRecyclerViewAdapter.setShoppingListWithIngredients(shoppingLists);
            shoppingFragmentRecyclerViewAdapter.notifyDataSetChanged();
        });
    }

    /**
    * setter for current shopping lists
    * */
    private void setCurrentShoppingLists(List<ShoppingListWithIngredients> shoppingListWithIngredients){
        this.allShoppingLists.addAll(shoppingListWithIngredients);
    }

    /**
    * initialise floating action button
    * for ShoppingList creation
    * */
    private void initFab(View root){
        fab = root.findViewById(R.id.shoppingFragmentFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateDialog(userOwnerId);
            }
        });
    }

    /**
    * create alert dialog to give new list a name
    * */
    private void showCreateDialog(int userOwnerId){
        // create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //create text box for name input
        final EditText edittext = new EditText(getContext());
        //set dialog message and title
        builder.setMessage("Enter a name for new shopping list:")
                .setTitle("Create new list")
                .setView(edittext);
        //set dialog positive option
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = edittext.getText().toString();
                if(!name.equals("")){
                    createNewShoppingList(name, userOwnerId);
                }else{
                    Toast.makeText(getContext(), "Cannot create list with empty name.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //set dialog negative option as null to dismiss
        builder.setNegativeButton("Cancel", null);
        builder.setIcon(R.drawable.ic_baseline_playlist_add_24);
        //create and show configured dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * creates new shopping list
     * @param name name of shopping list
     * @param userCreatorId id of the user, hardcoded to 1 for now
     */
    private void createNewShoppingList(String name, int userCreatorId){
        ShoppingListWithIngredients newList = new ShoppingListWithIngredients(
                new ShoppingList(name, 0, userCreatorId),
                new ArrayList<>());

        allShoppingLists.add(newList);
        mShoppingListViewModel.insert(newList.shoppingList);

        shoppingFragmentRecyclerViewAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "New shopping list '" + name + "' created!", Toast.LENGTH_SHORT).show();
    }

}
