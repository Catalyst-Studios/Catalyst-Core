**Cauldron Block Entity**
-

This Block Entity currently has 5 Recipe Types.

**1. Water Brewing**

**2. Lava Brewing**

**3. Universal Brewing**

**4. Fluid Brewing**

**5. Reaction Brewing**

To perform any recipe within the Cauldron, you can either Right-Click or Throw your Items into it to insert these, if the
recipe is set to use a fluid, you'll need to Right-Click the cauldron with a Bucket or any portable Tank to insert the fluids.

Each work similar but with tiny little differences.
___

**Water Brewing**, as the name suggest, only accepts Water as its Fluid Input and up to 4 Item Inputs. 

This recipe can be set to use a Heat Source, make sure to add the following block tag to work; { catalystcore:heat_sources }

Requires any Item with the following Tag to activate this recipe type; { catalystcore:cauldron/water_activator }
___

**Lava Brewing**, as the name suggest, only accept Lava as its Fluid Input and up to 4 Item Inputs. 

Doesn't require a heat source below because it's already hot enough.

Requires any Item with the following Tag to activate this recipe type; { catalystcore:cauldron/lava_activator }
___

**Universal Brewing**, this type is for those who are looking to use "any" fluid type as its input and up to 4 Item Inputs.

This recipe can be set to use a Heat Source, make sure to add the following block tag to work; { catalystcore:heat_sources }

Requires any Item with the following Tag to activate this recipe type; { catalystcore:cauldron/universal_activator }
___

**Fluid Brewing**, this one accepts any Fluid and 4 Items, similar to the Water Brewing Type, but it only outputs Fluids.

This recipe can be set to use a Heat Source, make sure to add the following block tag to work; { catalystcore:heat_sources }

Requires any Item with the following Tag to activate this recipe type; { catalystcore: cauldron/fluid_activator }
___

**Reaction Brewing**, my favourite type, as it works as the other 4 recipe types, accepts any Fluid and Item as Inputs.

This one, has 2 modes, Item and Fluid, this changes if the recipeType outputs an Item or a Fluid.

But, the only difference with this one, is that it **Blows Up** if the inputs are inserted incorrectly... perfect to remind players
that anything can go wrong hehe...

This recipe can be set to use a Heat Source, make sure to add the following block tag to work; { catalystcore:heat_sources }

Requires any Item with the following Tag to activate the Item output recipe type; { catalystcore:cauldron/item_reaction }

Requires any Item with the following Tag to activate the Fluid output recipe type; { catalystcore:cauldron/fluid_reaction }
___

There are some blocks that already have heat_sources Tag on them such as the Campfire and the Magma Block. And for the
activator tags, there are none, so you will need to add it to any Item manually.

It is advised to take any unwanted tags from Blocks or Items.

Inside this specific folder you may find all the examples you need for KubeJS or CraftTweaker. So make sure to check them out!
