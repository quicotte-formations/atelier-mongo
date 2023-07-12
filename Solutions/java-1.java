package mongoProjectTest;



import java.util.ArrayList;

import java.util.List;

import java.util.Random;



import org.bson.Document;



import com.mongodb.MongoClient;

import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;



public class Main {



    public static void main(String[] args) {

        // TODO Auto-generated method stub



        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");

        MongoClient client = new MongoClient(uri);



        MongoDatabase database = client.getDatabase("atelier");

        MongoCollection<Document> collection = database.getCollection("produits");





        for (int i = 0; i < 1000; i++) {



            Document product = new Document();

            product.append("num_serie", generateRandomSerialNumber());

            product.append("marque", generateRandomBrand());

            product.append("categorie", generateRandomCategory());



            List<Document> notes = new ArrayList<>();



            Random random = new Random();

            int numberOfClients = random.nextInt(5) + 1;



            for (int j = 0; j < numberOfClients; j++) {

                Document note1 = new Document("client", "Client 1")

                        .append("note", generateRandomNotes());

                notes.add(note1);

            }



            product.append("notes", notes);





            collection.insertOne(product);

        }



        client.close();



    }



    /**

     *

     * @return

     */

    private static String generateRandomSerialNumber() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder sb = new StringBuilder();



        Random random = new Random();

        int length = 8;



        for (int i = 0; i < length; i++) {

            int index = random.nextInt(characters.length());

            char randomChar = characters.charAt(index);

            sb.append(randomChar);

        }



        return sb.toString();

    }



    /**

     *

     * @return

     */

    private static String generateRandomBrand() {

        String[] brands = { "Nike", "Adidas", "Puma", "Reebok", "New Balance", "Converse", "Vans", "ASICS", "Fila" };



        Random random = new Random();

        int index = random.nextInt(brands.length);



        return brands[index];

    }



    /**

     *

     * @return

     */

    private static String generateRandomCategory() {

        String[] brands = { "Chaussure de ville", "Chaussure a talon", "Chaussure de marche", "Chaussure de rando"};



        Random random = new Random();

        int index = random.nextInt(brands.length);



        return brands[index];

    }



    /**

     *

     * @return

     */

    private static int generateRandomNotes() {

        Random r = new Random();

        int low = 1;

        int high = 5;

        int result = r.nextInt(high-low) + low;

        return result;



    }

}