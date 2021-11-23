package com.example;

import java.util.List;

public class LionAlex extends Lion {

    private Feline feline;

    public LionAlex(Feline feline) throws Exception {
        super("Самец", feline);
        this.feline = feline;
    }

    public List<String> getFriends() {
        return List.of("Марти", "Глория", "Мелман");
    }

    public String getPlaceOfLiving() {
        return "Нью-Йоркский зоопарк";
    }

    public int getKittens(int kittensCount) {
        return kittensCount;
    }

    @Override
    public int getKittens() {
        return getKittens(0);
    }
}
