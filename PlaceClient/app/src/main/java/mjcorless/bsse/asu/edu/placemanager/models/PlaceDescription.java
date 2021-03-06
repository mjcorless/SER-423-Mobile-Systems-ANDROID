package mjcorless.bsse.asu.edu.placemanager.models;

import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Copyright 2018 Matthew Corless
 * Copyright 2018 Your Name
 * This code is free to use for educational purposes.
 *
 * @author Matthew Corless
 * mailto: mjcorless@asu.edu
 * <p>
 * Helps manage the SQLite database for the app
 * <p>
 * Example PlaceDescription represented in Json
 * {
 * "name" : "ASU-Poly",
 * "description" : "Home of ASU's Software Engineering Programs",
 * "category" : "School",
 * "address-title" : "ASU Software Engineering",
 * "address-street" : "7171 E Sonoran Arroyo Mall\nPeralta Hall 230\nMesa AZ 85212",
 * "elevation" : 1384.0,
 * "latitude" : 33.306388,
 * "longitude" : -111.679121
 * }
 * NOTE: this implements Serializable because I was passing this class back and forth between activities. However, that is not the case anymore
 */
public class PlaceDescription implements Serializable
{
	public PlaceDescription()
	{}

	public PlaceDescription(JSONObject jsonObj)
	{
		try
		{
			System.out.println("constructor from json received: " + jsonObj.toString());
			name = jsonObj.getString("name");
			addressTitle = jsonObj.getString("address-title");
			addressPostal = jsonObj.getString("address-street");
			elevation = jsonObj.getDouble("elevation");
			latitude = (float) jsonObj.getDouble("latitude");
			longitude = (float) jsonObj.getDouble("longitude");
			image = jsonObj.getString("image");
			description = jsonObj.getString("description");
			category = jsonObj.getString("category");
		}
		catch (Exception ex)
		{
			System.out.println(this.getClass().getSimpleName() + ": error converting from json string");
		}
	}

	public JSONObject toJson()
	{
		JSONObject jo = new JSONObject();
		try
		{
			jo.put("name", name);
			jo.put("address-title", addressTitle);
			jo.put("address-street", addressPostal);
			jo.put("elevation", elevation);
			jo.put("latitude", latitude);
			jo.put("longitude", longitude);
			jo.put("image", image);
			jo.put("description", description);
			jo.put("category", category);
		}
		catch (Exception ex)
		{
			System.out.println(this.getClass().getSimpleName() + ": error converting to json");
		}
		return jo;
	}


	private static long serialVersionUID = -2518143671167959231L;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}


	public String getAddressTitle()
	{
		return addressTitle;
	}

	public double getElevation()
	{
		return elevation;
	}

	public void setElevation(double elevation)
	{
		this.elevation = elevation;
	}

	public float getLatitude()
	{
		return latitude;
	}

	public void setLatitude(float latitude)
	{
		this.latitude = latitude;
	}

	public float getLongitude()
	{
		return longitude;
	}

	public void setLongitude(float longitude)
	{
		this.longitude = longitude;
	}

	// A simple string, which is unique among all names of places for this user. Usually one or two words.
	private String name;

	// Text providing descriptive information about the place.
	private String description;

	// describes the type of place this entry describes.
	private String category;

	public void setAddressTitle(String addressTitle)
	{
		this.addressTitle = addressTitle;
	}

	public String getAddressPostal()
	{
		return addressPostal;
	}

	public void setAddressPostal(String addressPostal)
	{
		this.addressPostal = addressPostal;
	}

	private String addressTitle;

	private String addressPostal;

	// Feet mean sea level elevation of the place. This field is also generally not used in geocoding
	// services, but is useful in route planning.
	private double elevation;

	// Degrees latitude, using a single double value to represent. Lines of equal latitude run parallel
	// to the Equator. Values range from -90.0 to +90.0. Negative values refer to the southern hemisphere
	// and positive values to the northern hemisphere.
	private float latitude;

	// Degrees of longitude, using a single double value. Values range from -180.0 to +180.0. Lines
	// of equal longitude run perpendicular to the Equator. Negative values increase west from the
	// Prime Meridian, which is longitude 0.0 and is located in Greenwich England. Positive values
	// increase east from the Prime Meridian. 180.0 (plus and minus) is the International Date Line.
	private float longitude;

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	private String image;

	public void writeObject(ObjectOutputStream objectOS) throws IOException
	{
		objectOS.writeObject(getName());
		objectOS.writeObject(getDescription());
		objectOS.writeObject(getCategory());
		objectOS.writeObject(getAddressTitle());
		objectOS.writeObject(getAddressPostal());
		objectOS.writeObject(getElevation());
		objectOS.writeObject(getLatitude());
		objectOS.writeObject(getLongitude());
	}

	public void readObject(ObjectInputStream objectIS) throws IOException, ClassNotFoundException
	{
		setName((String) objectIS.readObject());
		setDescription((String) objectIS.readObject());
		setCategory((String) objectIS.readObject());
		setAddressTitle((String) objectIS.readObject());
		setAddressPostal((String) objectIS.readObject());
		setElevation((double) objectIS.readObject());
		setLatitude((float) objectIS.readObject());
		setLongitude((float) objectIS.readObject());

		validate();
	}

	private void validate() throws NullPointerException, IllegalArgumentException
	{
		// todo: how/should I validate elavation/longitude/latitude
		if (getName().isEmpty() || getDescription().isEmpty() || getCategory().isEmpty() || getAddressTitle() == null)
		{
			throw new IllegalArgumentException();
		}
	}
}
