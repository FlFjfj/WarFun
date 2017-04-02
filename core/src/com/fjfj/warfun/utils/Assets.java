package com.fjfj.warfun.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Array;

public class Assets {
	
	private static Array<Texture> textures;
	private static Array<String> textureNames;
	
	
	private static void loadTextures() {
		textures = new Array<Texture>();
		textureNames = new Array<String>();
		getSubDir(Gdx.files.local("assets/textures/"));
	}
	
	public static Texture getTexture(String name) {
		Texture texture = null;
		try{
		texture = textures.get(textureNames.indexOf("assets/textures/"+name, false));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Can't find image "+name);
		}
		return texture;
	}

	private static void getSubDir(FileHandle s){
		FileHandle dir = s;
		FileHandle[] files = dir.list();
		for (FileHandle file: files) {	
			if(file.isDirectory())
				getSubDir(file);
			if (file.name().contains(".png")
					||file.name().contains(".jpg")
					||file.name().contains(".PNG")
					||file.name().contains(".JPG")) {
				
				Texture texture = new Texture(file);
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

				textures.add(texture);
				textureNames.add(s+"/"+file.nameWithoutExtension());
			}
		}
	}
	
	public static void load () {
		loadTextures();
	}
}
