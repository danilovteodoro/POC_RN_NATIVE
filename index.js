import React, { Component } from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native';
import ImageBrowserApp from './App';
import App from './App';
import SimpleList from './SimpleList';


function HelloWorld() {
  console.log(this.props)
  return (
    <View style={styles.container}>
      <Text style={styles.hello}>HelloWorld</Text>
    </View>
  );
};
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    backgroundColor:'#ff0000'
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

AppRegistry.registerComponent(
  'MyReactNativeApp',
  () =>  App,
);

AppRegistry.registerComponent(
  "SimpleList", ()=> SimpleList
);