/// KUBEJS SCRIPT MADE BY CATALYST STUDIOS
/// THIS SCRIPT IS UNDER THE MIT LICENCE
/// THEREFORE THE USE OF THIS SCRIPT IN ANY PROJECT IS ALLOWED

ServerEvents.recipes(ratz =>
{
  ratz.custom(
  {
    type: 'catalystcore:pentagram', // Pentagram Recipe Type
    ingredients: // Only accepts 1 Input
    [
      {
      item: 'minecraft:dirt' // Item Input
      }
    ],
    result:
    {
      id: 'minecraft:gravel', // Item Output
      count: 1 // Output count can be any amount, but consider not exceeding 64 Items
    }
  })
})

/// KUBEJS SCRIPT MADE BY CATALYST STUDIOS
/// THIS SCRIPT IS UNDER THE MIT LICENCE
/// THEREFORE THE USE OF THIS SCRIPT IN ANY PROJECT IS ALLOWED