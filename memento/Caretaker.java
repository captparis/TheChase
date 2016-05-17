package memento;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Caretaker {

    private GameMemento memento;

    public GameMemento getMemento() {
    	try {
			FileInputStream fileIn = new FileInputStream("save.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			memento = (GameMemento) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("GameMemento class not found");
			c.printStackTrace();
			return null;
		}
        return memento;
    }

    public void setMemento(GameMemento memento) {
    	this.memento = (GameMemento) deepClone(memento);
    	// write memento in 'save.dat'
		try {
			FileOutputStream fileOut = new FileOutputStream("save.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.memento);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in 'save.dat'");
		} catch (IOException i) {
			i.printStackTrace();
		}
    }
    
    /**
	 * This method makes a "deep clone" of any Java object it is given.
	 */
	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
