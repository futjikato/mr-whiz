# Worldmap(s)

## Base structure

This is the base structure every worldmap should have.<br>
The root element of the XML mapfile is the world object.

    <?xml version="1.0" encoding="UTF-8"?>
    <world>
    </world>

Because I currently haven´t written a dtd file let me say that you must specify 3 child elements 
inside of the world root element.<br>

### Decorations

Not yet implemented.<br>
The plan is to allow you to place decoration objects on the map.

### Texture areas

Texture areas are a little more that pure graphics. In addition to provide the possibility to design 
the map they also says where a player can move.<br>
In the future there will be more options to come like walking speed and ... other stuff even I don´t know of yet.

    <textureAreas>
    	<area xywh="0,0,20,20" walkable="true" zIndex="0.0">
		  	<texture><![CDATA[images/backgrounds/grass02.png]]></texture>
  		</area>
    </textureAreas>

##### The grid/block system

The map is rendered blockwise. Which texture areas is rendered will be decided per block.<br>
All positioning information in the mapfile will be calculated in "blocks". One Block has a size of 50px*50px.<br>
So an area with xywh="2,2,5,8" will start on 100px*100px and has a width of 250px and a height of 400px.
    
    I should put an image here to make clear what i mean ...

##### Area elements

Inside and textureAreas-Tag can be multiple area Items. 
The area element defines a single texture area and stores all the needed information. With the _xywh_ Attribute 
you can specify where the area should be drawn. You will find this attribute a lot because it is used for nearly
all items that are drawn somewhere on the map. The Format is really simple and explains the name.<br>
The attribute should be a string with four comma seperated values. The first one is the X block

### Levels

The main goal of the worldmap is to let the player decide which level of the world he will approch next. 
That way the game feels less linear and we can hide fun stuff so the player has something to explor.
