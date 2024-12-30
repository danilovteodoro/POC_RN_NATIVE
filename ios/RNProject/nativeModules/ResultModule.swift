//
//  ResultModule.swift
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 18/04/24.
//

import Foundation
import React
protocol ResultModuleDelegate{
    func resultString(s: String)
}


@objc(ResultModule)
class ResultModule: RCTEventEmitter{
    var delegate:ResultModuleDelegate?
    
    @objc func resultString(_ s: String){
        if let delegate = self.delegate {
            delegate.resultString(s: s)
        }
    }
    
    @objc func resultObject(_ o: [String: Any]){
        let name = o["name"] as! String
        let age = o["city"] as! String
        let city = o["age"] as! Int
        
        print("Name: \(name), Age: \(age), City: \(city)")
    }
    
    @objc override  init() {
        super.init()
        print("init")
    }
    
    @objc override func supportedEvents() -> [String]! {
        return ["EventReminder"]
    }
    
    
    @objc func sendEvent(_ s: String){
        sendEvent(withName: "EventReminder", body: s)
    }
    
}
