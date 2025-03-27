package dev.zanckor.cobblemonridingfabric.config;

import java.util.*;

public class PokemonJsonObject {

    Boolean mustAllowEntityRiding = false;
    Map<String, PokemonConfigData> pokemonTypes = new HashMap<>();

    public PokemonJsonObject() {
    }

    public void add(String pokemonType, PokemonConfigData pokemonConfigData) {
        pokemonTypes.put(pokemonType, pokemonConfigData);
    }

    public boolean mustAllowEntityRiding() {
        return mustAllowEntityRiding == null ? false : mustAllowEntityRiding;
    }

    public static class PokemonConfigData {
        String formName;
        int stamina;
        ArrayList<MountType> mountType;
        ArrayList<Float> ridingOffSet;
        ArrayList<Float> movingRidingOffSet;
        ArrayList<ArrayList<Float>> passengersOffSet;
        ArrayList<ArrayList<Float>> movingPassengersOffSet;

        float speedModifier = 1;

        public PokemonConfigData(ArrayList<MountType> mountType, ArrayList<Float> offSet, ArrayList<ArrayList<Float>> passengersOffset, ArrayList<Float> movingOffSet, ArrayList<ArrayList<Float>> movingPassengersOffset) {
            this.mountType = mountType;
            this.ridingOffSet = offSet;
            this.passengersOffSet = passengersOffset;
            this.movingRidingOffSet = movingOffSet;
            this.movingPassengersOffSet = movingPassengersOffset;
        }

        public PokemonConfigData(ArrayList<MountType> mountType, ArrayList<Float> offSet, ArrayList<ArrayList<Float>> passengersOffset) {
            this(mountType, offSet, passengersOffset, null, null);
        }

        public PokemonConfigData(ArrayList<MountType> mountType, ArrayList<Float> offSet) {
            this(mountType, offSet, new ArrayList<>(), null, null);
            passengersOffSet.add(new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)));
        }

        public PokemonConfigData(ArrayList<MountType> mountType) {
            this(mountType, new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)), new ArrayList<>(), null, null);
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

        public ArrayList<Float> getRidingOffSet() {
            return ridingOffSet;
        }

       
        public ArrayList<Float> getMovingRidingOffSet() {
            return (movingRidingOffSet != null) ? movingRidingOffSet : getRidingOffSet();
        }

        public float getSpeedModifier() {
            return speedModifier;
        }

        public ArrayList<Float> getPassengerOffSet(int passenger) {
            return (passenger >= 0 && passenger < passengersOffSet.size()) ? passengersOffSet.get(passenger) : new ArrayList<>(List.of(0.0f, 0.0f, 0.0f));
        }

        public ArrayList<ArrayList<Float>> getPassengersOffSet() {
            return passengersOffSet;
        }

        public ArrayList<Float> getMovingPassengerOffSet(int passenger) {
            return (movingPassengersOffSet != null && passenger >= 0 && passenger < movingPassengersOffSet.size())
                    ? movingPassengersOffSet.get(passenger)
                    : getPassengerOffSet(passenger);
        }

        public ArrayList<ArrayList<Float>> getMovingPassengersOffSet() {
            return movingPassengersOffSet != null ? movingPassengersOffSet : passengersOffSet;
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
