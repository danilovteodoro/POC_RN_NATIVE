import React, { useEffect, useState } from "react";
import { Text, TouchableOpacity, StyleSheet, View, LogBox } from "react-native";
import ResultModule from "./native_modules/ResultModule";
import { emitter } from "./native_modules/ResultModule";



const SimpleList = () => {
    LogBox.ignoreAllLogs();
    LogBox.uninstall();
    const eventEmitter = emitter;
    let eventListener = eventEmitter.addListener('EventReminder', event => {
        console.log(event) // "someValue"
        const nNames = names.concat(event)
        setNames(() => {
            return nNames;
        })
    });
    const [names, setNames] = useState(
        [
            "RN Item 1",
            "RN Item 2",
            "RN Item 3",
            "RN Item 4",
            "RN Item 5"
        ]
    );

    return (
        names.map((l, i) => (
            <TouchableOpacity id="dd" onPress={() => {
                ResultModule.resultString(l);
                setNames(
                    names.filter((a, j) => j != i)
                );
            }} style={styles.container} key={i}>
                <View>
                    <Text style={styles.content}> {l}</Text>
                </View>
            </TouchableOpacity>
        ))

    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
       
        justifyContent: "center",
        padding: 1.5,
        borderBottomWidth: 0.3,
        borderBlockColor: "#11111188",
        height: 50,
        maxHeight: 50
    },
    content: {
        fontWeight: "500",
        color: '#ff0000'
    }
});

export default SimpleList;