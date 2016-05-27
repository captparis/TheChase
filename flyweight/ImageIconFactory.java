package flyweight;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class ImageIconFactory {
	public static final Map<String, ImageIcon> iconMap = new HashMap<>();
	
	public static ImageIcon getIcon(String name){
		
		ImageIcon icon = iconMap.get(name);
		
		if(icon == null){
			icon = new ImageIcon("bin/images/" + name + ".png");
			iconMap.put(name, icon);
		}
		
		return icon;
		
	}
}
