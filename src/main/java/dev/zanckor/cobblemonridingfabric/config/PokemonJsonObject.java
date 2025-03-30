package dev.zanckor.cobblemonridingfabric.config;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class PokemonJsonObject {

    Boolean mustAllowEntityRiding = false;
    Map<String, PokemonConfigData> pokemonTypes = new HashMap<>();

    public PokemonJsonObject() {
    }

    public void add(String pokemonType, PokemonConfigData pokemonConfigData) {
        pokemonTypes.put(pokemonType, pokemonConfigData);
    }

    public boolean mustAllowEntityRiding() {
        return mustAllowEntityRiding != null && mustAllowEntityRiding;
    }

    public static class OffsetData {
        @SerializedName("default")
        ArrayList<Float> base = new ArrayList<>(List.of(0.0f, 0.0f, 0.0f));

        @SerializedName("moving")
        ArrayList<Float> moving;

        @SerializedName("flying")
        ArrayList<Float> flying;

        @SerializedName("flying:moving")
        ArrayList<Float> flyingMoving;

        @SerializedName("flying:idle")
        ArrayList<Float> flyingIdle;

        @SerializedName("swimming")
        ArrayList<Float> swimming;

        @SerializedName("swimming:moving")
        ArrayList<Float> swimmingMoving;

        @SerializedName("swimming:idle")
        ArrayList<Float> swimmingIdle;

        @SerializedName("swimming:lava")
        ArrayList<Float> swimmingLava;

        @SerializedName("swimming:lava:moving")
        ArrayList<Float> swimmingLavaMoving;

        @SerializedName("swimming:lava:idle")
        ArrayList<Float> swimmingLavaIdle;

        public OffsetData() {
        }

        public OffsetData(ArrayList<Float> base) {
            this.base = base;
        }

        public ArrayList<Float> getDefault() {
            return base;
        }

        public ArrayList<Float> getMoving() {
            return moving != null ? moving : base;
        }

        public ArrayList<Float> getFlying() {
            return flying != null ? flying : base;
        }

        public ArrayList<Float> getFlyingMoving() {
            return flyingMoving != null ? flyingMoving : getFlying();
        }

        public ArrayList<Float> getFlyingIdle() {
            return flyingIdle != null ? flyingIdle : getFlying();
        }

        public ArrayList<Float> getSwimming() {
            return swimming != null ? swimming : base;
        }

        public ArrayList<Float> getSwimmingMoving() {
            return swimmingMoving != null ? swimmingMoving : getSwimming();
        }

        public ArrayList<Float> getSwimmingIdle() {
            return swimmingIdle != null ? swimmingIdle : getSwimming();
        }

        public ArrayList<Float> getSwimmingLava() {
            return swimmingLava != null ? swimmingLava : base;
        }

        public ArrayList<Float> getSwimmingLavaMoving() {
            return swimmingLavaMoving != null ? swimmingLavaMoving : getSwimmingLava();
        }

        public ArrayList<Float> getSwimmingLavaIdle() {
            return swimmingLavaIdle != null ? swimmingLavaIdle : getSwimmingLava();
        }
    }

    public static class PassengerOffsetData {
        @SerializedName("default")
        ArrayList<ArrayList<Float>> base = new ArrayList<>();

        @SerializedName("moving")
        ArrayList<ArrayList<Float>> moving;

        @SerializedName("flying")
        ArrayList<ArrayList<Float>> flying;

        @SerializedName("flying:moving")
        ArrayList<ArrayList<Float>> flyingMoving;

        @SerializedName("flying:idle")
        ArrayList<ArrayList<Float>> flyingIdle;

        @SerializedName("swimming")
        ArrayList<ArrayList<Float>> swimming;

        @SerializedName("swimming:moving")
        ArrayList<ArrayList<Float>> swimmingMoving;

        @SerializedName("swimming:idle")
        ArrayList<ArrayList<Float>> swimmingIdle;

        @SerializedName("swimming:lava")
        ArrayList<ArrayList<Float>> swimmingLava;

        @SerializedName("swimming:lava:moving")
        ArrayList<ArrayList<Float>> swimmingLavaMoving;

        @SerializedName("swimming:lava:idle")
        ArrayList<ArrayList<Float>> swimmingLavaIdle;

        public PassengerOffsetData() {
        }

        public PassengerOffsetData(ArrayList<ArrayList<Float>> base) {
            this.base = base;
        }

        public ArrayList<ArrayList<Float>> getDefault() {
            return base;
        }

        public ArrayList<ArrayList<Float>> getMoving() {
            return moving != null ? moving : base;
        }

        public ArrayList<ArrayList<Float>> getFlying() {
            return flying != null ? flying : base;
        }

        public ArrayList<ArrayList<Float>> getFlyingMoving() {
            return flyingMoving != null ? flyingMoving : getFlying();
        }

        public ArrayList<ArrayList<Float>> getFlyingIdle() {
            return flyingIdle != null ? flyingIdle : getFlying();
        }

        public ArrayList<ArrayList<Float>> getSwimming() {
            return swimming != null ? swimming : base;
        }

        public ArrayList<ArrayList<Float>> getSwimmingMoving() {
            return swimmingMoving != null ? swimmingMoving : getSwimming();
        }

        public ArrayList<ArrayList<Float>> getSwimmingIdle() {
            return swimmingIdle != null ? swimmingIdle : getSwimming();
        }

        public ArrayList<ArrayList<Float>> getSwimmingLava() {
            return swimmingLava != null ? swimmingLava : base;
        }

        public ArrayList<ArrayList<Float>> getSwimmingLavaMoving() {
            return swimmingLavaMoving != null ? swimmingLavaMoving : getSwimmingLava();
        }

        public ArrayList<ArrayList<Float>> getSwimmingLavaIdle() {
            return swimmingLavaIdle != null ? swimmingLavaIdle : getSwimmingLava();
        }

    }

    public static class PokemonConfigData {
        String formName;
        int stamina;
        ArrayList<MountType> mountType;
        OffsetData ridingOffset = new OffsetData();
        PassengerOffsetData passengersOffset = new PassengerOffsetData();
        float speedModifier = 1;

        public PokemonConfigData(ArrayList<MountType> mountType, OffsetData ridingOffset,
                PassengerOffsetData passengersOffset) {
            this.mountType = mountType;
            this.ridingOffset = ridingOffset;
            this.passengersOffset = passengersOffset;
        }

        public PokemonConfigData(ArrayList<MountType> mountType, OffsetData ridingOffset) {
            this(mountType, ridingOffset, new PassengerOffsetData());
        }

        public PokemonConfigData(ArrayList<MountType> mountType) {
            this(mountType, new OffsetData(), new PassengerOffsetData());
        }

        public PokemonConfigData() {
            this(new ArrayList<>(List.of(MountType.WALK)));
        }

        public int getMaxStamina() {
            return stamina != 0 ? stamina : 200;
        }

        public String getFormName() {
            return formName == null ? "none" : formName;
        }

        public ArrayList<MountType> getMountTypes() {
            return mountType;
        }

        public OffsetData getRidingOffset() {
            return ridingOffset;
        }

        public PassengerOffsetData getPassengersOffset() {
            return passengersOffset;
        }

        public float getSpeedModifier() {
            return speedModifier;
        }
    }

    public Set<String> getPokemonIDs() {
        return pokemonTypes.keySet();
    }

    public PokemonConfigData getPokemonData(String pokemonType) {
        return pokemonTypes.get(pokemonType);
    }

    public enum MountType {
        WALK,
        SWIM,
        LAVA_SWIM,
        FLY
    }
}
