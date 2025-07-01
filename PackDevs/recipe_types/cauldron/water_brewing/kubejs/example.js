/// KUBEJS SCRIPT MADE BY CATALYST STUDIOS
/// THIS SCRIPT IS UNDER THE MIT LICENCE
/// THEREFORE THE USE OF THIS SCRIPT IN ANY PROJECT IS ALLOWED

ServerEvents.recipes(ratz =>
{
  ratz.custom(
  {
    type: 'catalystcore:water_brewing',
    ingredients:
    [
      {
      item: 'minecraft:gravel' /// Change this to any Item you want
      },
      {
      item: 'minecraft:dirt' /// Change this to any Item you want
      },
      {
      item: 'minecraft:gravel' /// Change this to any Item you want
      },
      {
      item: 'minecraft:dirt' /// Change this to any Item you want
      }, /// Min Items - 1 | Max Items - 4
    ],
    output:
    {
      id: 'minecraft:diamond' /// Change this to any Item you want
    },
    fluidAmount: 1000, /// Change to the amount of Water you want to use | Max amount - 4B
    cookingTime: 20, /// Change to set Cooking Time
    requiresHeat: true /// True - Needs Heat Source | False - No Heat Source
  });
});

/// KUBEJS SCRIPT MADE BY CATALYST STUDIOS
/// THIS SCRIPT IS UNDER THE MIT LICENCE
/// THEREFORE THE USE OF THIS SCRIPT IN ANY PROJECT IS ALLOWED