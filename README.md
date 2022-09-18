# Bundle Calculator Code Challenge

This repo is intended to challenge the [bundle-calculator](bundle-calculator.md) code test.

### 🤔 What the thoughts and processes I had when solving this challenge?

First up, reading the challenge description, I guess there are 3 `entities` of this scenario here.
- The `Order` from the user, consisting of `quantity` and `productCode`
- The `Bundle` which consist of `size` of bundle and `price` per bundle.
- The `Product` that forms with `formatCode` that represents the product and available `bundles` for it.

As for the calculator itself, it feels more like a `service` to me rather than a entity. `BundleCalculator` contains following properties.
- `readOrderFromUser` that prompts a few message and ask the user to enter the order.
- `checkProductAvailability` will check if available products satisfies the order.
- `findBestBundleCombination` iterate through the bundles of the matching product and find the best combination or return an empty list if none can be found.
- `calculateTotalOfBundles` simply just calculate the sum of bundle combination.
- `generateResponse` did the final work of finalizing the process and inform user of the result.

There are also a couple `utils` class in this project
- `CustomHelper` (can't find a better name for it) have (both are shamelessly stolen from stackoverflow)
  - a custom collector - `toSingleton` that helps me extract an object from the stream operation.
  - a `count` method that count the number of reoccurring object from the given list
- `JSON` was shamelessly stolen from the world of `js`, it basically uses `gson` to convert an Java object to json string.
- `Logger` was made to have one instance managing the logging behaviour, however, it didn't grew much apart from helping me test which logger actually works for me. And turns out `System.out.println` works best to my intention? 🤔

And that's pretty much it?

### ⏭️ What's next for this project?
Well... As of this commit.
- [x] Rest my pig brain for the day.
- [ ] Add unit tests where suitable.
- [ ] Add comments to code where needed.
- [ ] Research if there are alternative solution and refactor stolen code.
- [ ] Sure is something there but can't think of any at this point. 🤯

### 🏃 How to run this project?

For Windows User:

If you are opening this project with IntelliJ IDEA, you can (should be able to) simply hit `Shift + F10` to run `Main.main()`.
If you see a message from `Ocelot`, then congrats! you just managed to run this project! 🥳🥳

If you want to run this project with commands, then to be honest, I don't know how to do that yet. 🥲🥲

For Mac User:

I think hitting `Command + R` works?

### 🧪 How to test this project?

If you are opening this project with IntelliJ Idea, you can (should be able to) simply hit `Shift + F10` to run `Main.main()`...

Just kidding, you need to visit `src/test/...../MainTest` and click on the ▶ next to `whenRunMain_thenSayHelloWorld`.

With command? 🥲🥲

### 🏆 Who make this solution possible?
- Special thanks to 锤姐 and 劳哥 for providing this opportunity and reminder to follow object-oriented design. 
- Special thanks to Alex for helping me understand what dynamic programming is about, thought, the solution did not use any of that approach.
