package domain.db;

import domain.model.PartyEquipment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsInDb {
   private Map<Integer, PartyEquipment> records = new HashMap<>();

   public ProductsInDb(){
      //readFromFile();
      add(new PartyEquipment(5.0,"balloon"));
      add(new PartyEquipment(15.0,"firework"));
      add(new PartyEquipment(20.0,"various items"));
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

   public int getNumberOfProducts(){
      return records.size();
   }

  // @SuppressWarnings("unchecked")
//   public void readFromFile(){
//
//      JSONParser jsonParser = new JSONParser();
//
//      try (FileReader reader = new FileReader("products.json"))
//      {
//         //Read JSON file
//         Object obj = jsonParser.parse(reader);
//
//         JSONArray employeeList = (JSONArray) obj;
//         System.out.println(employeeList);
//
//         //Iterate over employee array
//         employeeList.forEach( emp -> parseEquipmentObject( (JSONObject) emp ) );
//
//      } catch (IOException | ParseException e) {
//         e.printStackTrace();
//      }
//
//   }
//
//   private static void parseEquipmentObject(JSONObject equipment){
//      JSONObject equipmentObject = (JSONObject) equipment.get("equipment");
//
//      String name = (String) equipmentObject.get("name");
//      System.out.println(name);
//
//      Double price = (Double) equipmentObject.get("price");
//      System.out.println(price);
//   }
//
//   @SuppressWarnings("unchecked")
//   public void writeToList(){
//      JSONObject equipmentDetails = new JSONObject();
//      equipmentDetails.put("name", "firework");
//      equipmentDetails.put("price", "2.0");
//
//      JSONObject equipmentObject = new JSONObject();
//      equipmentObject.put("equipment", equipmentDetails);
//
//      JSONArray equiptmentList = new JSONArray();
//      equiptmentList.add(equipmentObject);
//
//      try (FileWriter file = new FileWriter("products.json")) {
//
//         file.write(equiptmentList.toJSONString());
//         file.flush();
//
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//   }

}
