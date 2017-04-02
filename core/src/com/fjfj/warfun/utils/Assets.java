package com.fjfj.warfun.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Array;

public class Assets {
	
	private static Array<Texture> textures;
	private static Array<String> textureNames;
	
	public static Music music;
	public static Sound hit;
	public static Sound pill;
	public static Sound rainbow;
	
	
	private static void loadTextures() {
		//MusicLoader ml = new MusicLoader().lo
		
		music = Gdx.audio.newMusic(Gdx.files.local("assets/sounds/music.mp3"));
		hit = Gdx.audio.newSound(Gdx.files.local("assets/sounds/hit.wav"));
		pill = Gdx.audio.newSound(Gdx.files.local("assets/sounds/pill.wav"));
		rainbow = Gdx.audio.newSound(Gdx.files.local("assets/sounds/rainbow.wav"));
		
		textures = new Array<Texture>();
		textureNames = new Array<String>();
		getSubDir(Gdx.files.local("assets/textures/"));
		
		music.setLooping(true);
		music.play();
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
