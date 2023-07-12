package intro_exercice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.bson.Document;




import com.mongodb.MongoClient;

import com.mongodb.MongoClientURI;

import com.mongodb.client.AggregateIterable;

import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;




public class Main {




public static void main(String[] args) {

// Connection to MongoDB database

MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");

MongoClient mongoClient = new MongoClient(uri);

// Selecting a database

MongoDatabase database = mongoClient.getDatabase("atelierjavamongodbconnection");

// Selecting a collection

MongoCollection<Document> collection = database.getCollection("produits");

// collection.deleteMany(new Document());

// Insertion to database

for (int i = 0; i < 1000; i++) {

// Document document = new Document();

// document.append("categorie", getRandomCategory());

// document.append("marque", getRandomBrand());

// document.append("numSerie", getRandomNumSerie());

// document.append("prix", getRandomPrix());

// document.append("note", Arrays.asList(new Document("client", getRandomNom()).append("noteSurCinq", getRandomMark())));

// collection.insertOne(document);

}

// Agregation pipeline

// Document unwindStage = new Document("$unwind", "$marque");

// Document groupeStage = new Document("$group",

// new Document("_id", "$marque"));

Document matchStage = new Document("$match",

new Document("categorie", "Tablette"));

Document sortStage = new Document("$sort", new Document("_id", 1));

// Agregation

AggregateIterable<Document> results = collection.aggregate(Arrays.asList(matchStage, sortStage));

// Display results

for (Document doc : results) {

System.out.println(doc.toJson());

}

// Closing the MongoDB connection

mongoClient.close();

}

// Functions to generate random data

public static String getRandomCategory() {

ArrayList<String> categories = new ArrayList<>(Arrays.asList("Tablette", "Smartphone", "PC", "TV"));

Random random = new Random();

int randomitem = random.nextInt(categories.size());

String randomCategory = categories.get(randomitem);

return randomCategory;

}

public static String getRandomBrand() {

ArrayList<String> brands = new ArrayList<>(Arrays.asList("Samsung", "Sony", "Xiaomi", "Huwawei", "HTC"));

Random random = new Random();

int randomitem = random.nextInt(brands.size());

String randomBrand = brands.get(randomitem);

return randomBrand;

}

public static int getRandomMark() {

int randomMark = (int) (Math.random() * 6);

return randomMark;

}

public static int getRandomPrix() {

int[] prix = {320, 450, 620, 768, 864, 885, 950, 999};

int randomPrix = prix[(int) Math.floor(Math.random() * 8)];

return randomPrix;

}

public static String getRandomNom() {

Random random = new Random();

String[] noms = {"John", "Marc", "Laura", "Ted", "Sophie", "Stéphane", "Cédric", "Martin", "Julie"};

String randomNom = noms[random.nextInt(noms.length)];

return randomNom;

}

public static String getRandomNumSerie() {

Random random = new Random();

String numSerie = "SN" + random.nextInt(1000000);

return numSerie;

}




}

