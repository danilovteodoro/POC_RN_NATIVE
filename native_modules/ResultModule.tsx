import { NativeEventEmitter, NativeModules } from "react-native";
const {ResultModule} = NativeModules;

export type Person = {
    name: String;
    age: number;
    city: String
}

interface Result {
    resultString(s: String): void
    resultObject(p: Person): void 
}

export default ResultModule as Result;
export const emitter = new NativeEventEmitter(ResultModule)

