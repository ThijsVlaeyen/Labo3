package domain.db;

import domain.model.PartyEquipment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProductsInDb {
   private static Map<Integer, PartyEquipment> records = new HashMap<>();

   public ProductsInDb(){
      readFromFile();
   }

   public PartyEquipment get(int id){
      if (id < 0){
         throw new DbException("No valid id given");
      }
      return records.get(id);
   }

   public List<PartyEquipment> getAll(){
      return new ArrayList<PartyEquipment>(records.values());
   }

   public void add(PartyEquipment equipment){
      if (equipment == null){
         throw new DbException("No product given");
      }
      int id = records.size() + 1;
      equipment.setId(id);
      if (records.containsKey(equipment.getId())){
         throw new DbException("Product already exists");
      }
      records.put(equipment.getId(), equipment);
   }

   public void update(PartyEquipment equipment){
      if(equipment == null){
         throw new DbException("No product given");
      }
      if (!records.containsKey(equipment.getId())){
         throw new DbException("No product found");
      }
      records.put(equipment.getId(), equipment);
   }

   public void delete(int id){
      if (id < 0){
         throw new DbException("No valid id given");
      }
      if (records.remove(id) == null){
         throw new DbException("this product doesn't exist");
      }
   }

   private void removeAll(){
      for (PartyEquipment p : this.records.values()){
         if (p.getState()==p.getRemove()){
            delete(p.getId());
         }
      }
   }

   public int getNumberOfProducts(){
      return records.size();
   }

   @SuppressWarnings("unchecked")
   public void readFromFile(){

      JSONParser jsonParser = new JSONParser();

      try (FileReader reader = new FileReader("./src/domain/db/products.json"))
      {
         //Read JSON file
         Object obj = jsonParser.parse(reader);

         JSONArray productsList = (JSONArray) obj;

         //Iterate over employee array
         productsList.forEach( product -> parseEquipmentObject( (JSONObject) product ) );

      } catch (IOException | ParseException e) {
         e.printStackTrace();
      }

   }

   private void parseEquipmentObject(JSONObject equipment){
      JSONObject equipmentObject = (JSONObject) equipment.get("equipment");

      String name = (String) equipmentObject.get("name");

      Double price = (Double) equipmentObject.get("price");

      add(new PartyEquipment(price, name));
   }

   @SuppressWarnings("unchecked")
   public static void writeToFile(){
      JSONArray equiptmentList = new JSONArray();

      for (Map.Entry<Integer, PartyEquipment> entry : records.entrySet()) {
         JSONObject equipmentDetails = new JSONObject();
         equipmentDetails.put("name", entry.getValue().name);
         equipmentDetails.put("price", entry.getValue().getPrice());

         JSONObject equipmentObject = new JSONObject();
         equipmentObject.put("equipment", equipmentDetails);

         equiptmentList.add(equipmentObject);
      }

      try (FileWriter file = new FileWriter("./src/domain/db/products.json")) {

         file.write(equiptmentList.toJSONString());
         file.flush();

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
