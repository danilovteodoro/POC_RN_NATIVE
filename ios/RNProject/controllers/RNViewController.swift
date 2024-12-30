//
//  RNViewController.swift
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 17/04/24.
//

import UIKit
import React

class RNViewController: UIViewController {
    var delegate: TextProtocol?
//    private var rootView: RCTRootView? = nil
    var initialProps: NSDictionary = [:]
    var rct: RCTRootView?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initRootView()
    }
    
  
    private func initRootView(){
        let bridge = getBridge()
        
        let initialProps: NSDictionary = [
            "name":"value"
        ]
        self.rct = RCTRootView(
            bridge: bridge,
            moduleName: "MyReactNativeApp",
            initialProperties: initialProps as [NSObject: AnyObject])
        
        self.view = self.rct
    }
    
    private func getBridge() -> RCTBridge{
    //  let jsCodeLocation = URL(string: "http://localhost:8081/index.bundle?platform=ios")!
        let jsCodeLocation = Bundle.main.url(forResource: "main", withExtension: "jsbundle")!
        
        let resultModule = ResultModule()
        resultModule.delegate = self
        let bridge = RCTBridge(bundleURL: jsCodeLocation) {
            [resultModule] as? [RCTBridgeModule]
        }!
        
        return bridge
    }
}

extension RNViewController: ResultModuleDelegate {
    func resultString(s: String) {
        guard let delegate = self.delegate else{
            return
        }
        
        DispatchQueue.main.async {
            self.dismiss(animated: true)
            delegate.resultText(s: s)
        }
    }
    
    
}
