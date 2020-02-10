package Classes;

import java.util.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

public class RetrieveTechniques {

    //name and tag are in reference to the database keys. Name is the name of the technique. Tags are things associated with the technique
    private final String nameConst = "name";
    private final String tagConst = "tag";
    private final String urlConst = "url";
    private final String databaseName = "CulinaryCompanion";
    private final String collectionName = "Techniques";
    private final String getRolled = "https://www.youtube.com/embed/dQw4w9WgXcQ";
    private final String mongoGetString = "$gte";
    private final String bigBoiString = "mongodb+srv://Grant:Turtle@cluster0-knqq3.mongodb.net/test?retryWrites=true&w=majority";

    public String getNameConst() {
        return nameConst;
    }

    public String getTagConst() {
        return tagConst;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getGetRolled() {
        return getRolled;
    }

    public String getUrlConst() {
        return urlConst;
    }

    public String getMongoGetString() {
        return mongoGetString;
    }

    public String getBigBoiString() {
        return bigBoiString;
    }

    public RetrieveTechniques() { };

    // So this first function retrieves just the related youtubeURL by name of the recipe. I believe this is what you'll want to use mark.
    // This might not work perfectly, but for now it should. Might have to change over to searching for tag instead. Let me know.
    public String getTechniqueUrlByName(String searchName)
    {
        String youtubeURL = getGetRolled();
        MongoClientURI connectionString = new MongoClientURI(getBigBoiString());

        try {
            MongoClient mongo = new MongoClient(connectionString);
            MongoDatabase CCDatabase = mongo.getDatabase(getDatabaseName());
            MongoCollection<Document> techniquesCollection = CCDatabase.getCollection(getCollectionName());

            Document findQuery = new Document(getNameConst(), new Document(getMongoGetString(), searchName));

            MongoCursor<Document> cursor = techniquesCollection.find(findQuery).iterator();
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                youtubeURL = doc.get(getUrlConst()).toString();
            }
            cursor.close();

            System.out.println("successful connection and query");
        } catch (Exception e)
        {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

        return youtubeURL;
    }

    //This will return the whole technique by it's search name
    public Document getTechniqueByName(String searchTag)
    {
        Document techniqueInformation = new Document();
        MongoClientURI connectionString = new MongoClientURI(getBigBoiString());

        try {
            MongoClient mongo = new MongoClient(connectionString);
            MongoDatabase CCDatabase = mongo.getDatabase(getDatabaseName());
            MongoCollection<Document> techniquesCollection = CCDatabase.getCollection(getCollectionName());

            Document findQuery = new Document(getNameConst(), new Document(getMongoGetString(), searchTag));

            MongoCursor<Document> cursor = techniquesCollection.find(findQuery).iterator();

            techniqueInformation = cursor.next();
            cursor.close();

            System.out.println("successful connection and query");
        } catch (Exception e)
        {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

        return techniqueInformation;
    }

    //This will return a recipe technique once those are populated hopefully. Not fully finished yet.
    public Document getRecipeTechniqueByName(String searchName)
    {
        Document techniqueInformation = new Document();
        MongoClientURI connectionString = new MongoClientURI(getBigBoiString());

        try {
            MongoClient mongo = new MongoClient(connectionString);
            MongoDatabase CCDatabase = mongo.getDatabase(getDatabaseName());
            MongoCollection<Document> techniquesCollection = CCDatabase.getCollection(getCollectionName());

            Document findQuery = new Document(getNameConst(), new Document(getMongoGetString(), searchName));

            MongoCursor<Document> cursor = techniquesCollection.find(findQuery).iterator();
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                techniqueInformation = doc;
            }
            cursor.close();

            System.out.println("successful connection and query");
        } catch (Exception e)
        {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

        return techniqueInformation;
    }
}