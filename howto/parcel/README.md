# How to use Indigo with Parcel.js

This project takes the standard ["sbt hello indigo"](https://github.com/PurpleKingdomGames/hello-indigo-sbt) project but runs it in a browser - with hot reloading - via [parcel](https://parceljs.org/).

Parcel.js is like webpack but requires very little config to "just work". You could use any bundler you like.

The files of interest are:

1. `package.json` - tells yarn what to do
1. `build.sbt` - which now emits indigo as a common module.
1. `gamestart.js` - Loads the game
1. `index.html` - which is a copy of the normal index file created by indigo, modified slightly.

I'm using [yarn](https://yarnpkg.com/), but I imagine npm will work too.

From the command line on first run:

```bash
yarn install
```

Then to run your game

```bash
yarn start
```

and navigate to [http://localhost:1234/](http://localhost:1234/).

There is also a `yarn build` command if you want to do a clean build rather than running the game now.

## Hot reloading

Hot reloading in this case is restarting the game whenever a source file changes.

### Game source

`gamestart.js` is not referring the javascript in the `indigoBuild`, instead it's look straight at the `fastOptJS` output. Which means that if you run `sbt fastOptJS` in another terminal window, parcel will notice the changes and reload the game for you.

### Assets

Similarly, a glob pattern is defined in `package.json` telling parcel to copy over static assets whenever they change too. Nice if you're working on a shader in an external glsl file or something.
