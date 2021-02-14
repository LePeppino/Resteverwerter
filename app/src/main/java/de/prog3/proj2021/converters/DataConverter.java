package de.prog3.proj2021.converters;

/**
 * Data Converter
 * NOT USED
 *
 * @author Giuseppe Buccellato
 */

import androidx.room.TypeConverter;

import java.util.Date;

import de.prog3.proj2021.models.Ingredient;


public class DataConverter {

    /**
    * Date converters
    * */
    @TypeConverter
    public static Date toDate(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value){
        return value == null ? null : value.getTime();
    }

    /*
    * Enum converters
    * */
    //convert from Unit to integer
    @TypeConverter
    public static int fromUnitToInteger(Ingredient.Unit unitValue){
        return unitValue.ordinal();
    }

    //convert integer to Unit
    @TypeConverter
    public static Ingredient.Unit fromIntegerToUnit(int unitValue){
        return (Ingredient.Unit.values()[unitValue]);
    }

    //convert Type to integer
    @TypeConverter
    public static int fromTypeToInteger(Ingredient.Type typeValue){
        return typeValue.ordinal();
    }

    //convert integer to Type
    @TypeConverter
    public static Ingredient.Type fromIntegerToType(int typeValue){
        return (Ingredient.Type.values()[typeValue]);
    }
}
