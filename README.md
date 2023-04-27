# breakout
remove all bricks without letting the ball fall off the bottom of the screen
sh -c javac -classpath .:target/dependency/* -d . $(find . -type f -name '*.java')
java -classpath .:target/dependency/* Main
