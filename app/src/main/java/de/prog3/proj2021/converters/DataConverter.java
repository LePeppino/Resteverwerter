package de.prog3.proj2021.converters;

/**
 * Data Converter
 * NOT CURRENTLY IN USE
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

    /**
    * Enum converters
    * */
    @TypeConverter
    public static int fromUnitToInteger(Ingredient.Unit unitValue){
        return unitValue.ordinal();
    }

    @TypeConverter
    public static Ingredient.Unit fromIntegerToUnit(int unitValue){
        return (Ingredient.Unit.values()[unitValue]);
    }

    @TypeConverter
    public static int fromTypeToInteger(Ingredient.Type typeValue){
        return typeValue.ordinal();
    }

    @TypeConverter
    public static Ingredient.Type fromIntegerToType(int typeValue){
        return (Ingredient.Type.values()[typeValue]);
    }
}
