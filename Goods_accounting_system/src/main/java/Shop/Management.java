package Shop;

import java.util.ArrayList;

public class Management {
    private ArrayList<FurnitureBase> listCards = new ArrayList();
    // Добавить описание врезных замков
    public void add(String name, int weight, String color, int width,int length, int cost, int assembled_lengh){
        Sofas cardSofas = new Sofas(getNextID(),name, weight, color, width,length, cost,assembled_lengh);
        listCards.add(cardSofas);
    }
    // Добавить описание навесных замков
    public void add(String name, int weight, String color,int width,int length,int cost,String mattress){
        Beds cardBeds = new Beds(getNextID(),name, weight, color, width, length,cost, mattress);
        listCards.add(cardBeds);
    }
    private int getNextID(){
        int nextID;
        boolean freeID = true;
        do {
            freeID = true;
            nextID = (int)(Math.random() * 100000);
            for (int i = 0; i < listCards.size(); i++) {
                if (listCards.get(i).getId() == nextID)
                    freeID = false;
            }
        }while (freeID == false);
        return nextID;
    }
    public void removeCard(int index){
        if (index >= listCards.size())
            return;
        listCards.remove(index);
    }
    public FurnitureBase getCard(int index){
        if (index >= listCards.size())
            return null;
        return listCards.get(index);
    }
    public FurnitureBase getOf(int index){
        ArrayList<FurnitureBase> listReturn = new ArrayList<FurnitureBase>();
        for (int i = 0; i < listCards.size(); i++){
            FurnitureBase card = listCards.get(i);
            if (card.getId()==index)
                return card;
        }
        return null;
    }
    public FurnitureBase getAll(int row){
        if (listCards.size()>row){
            return listCards.get(row);
        }
        return null;
    }
    public ArrayList<FurnitureBase> findCard(String name){
        ArrayList<FurnitureBase> listReturn = new ArrayList<FurnitureBase>();
        for (int i = 0; i < listCards.size(); i++){
            FurnitureBase card = listCards.get(i);
            if (card.getName().contains(name))
                listReturn.add(card);
        }
        return listReturn;
    }
    public int getCount(){
        return listCards.size();

    }

}