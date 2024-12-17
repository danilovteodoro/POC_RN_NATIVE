import React from 'react';
import {View,StyleSheet,Text, ScrollView,Button, TextInput} from 'react-native';
import ResultModule from './native_modules/ResultModule';
import { LogBox } from 'react-native';


const App = ({name})=> {
  LogBox.ignoreAllLogs();
  LogBox.uninstall();
      const [text, onChangeText] = React.useState('');

      return (
        
        <ScrollView contentContainerStyle={styles.container}>
          <Text style={styles.hello}>{name}</Text>
        <View style={styles.btn}>
         <TextInput
            style={styles.input}
            onChangeText={onChangeText}
            value={text}
          /> 
        <Button
          title="Send back to native implementation"
          color="#841584"
          onPress={()=> onSubmit(text)}
          />
        </View>
    
        </ScrollView>
      );
    }
  export default App;

const onSubmit =(text)=>{
    ResultModule.resultString(text);
    // ResultModule.resultObject({name:text, age:35, city:"Goiania"})
    
};

const styles = StyleSheet.create({
    container: {
      flex: 1,
      justifyContent: 'center',
    },
    hello: {
      fontSize: 20,
      textAlign: 'center',
      margin: 10,
    },
    btn:{
        margin: 10,
    },
    input: {
        height: 40,
        margin: 12,
        borderWidth: 1,
        padding: 10,
      },
  });