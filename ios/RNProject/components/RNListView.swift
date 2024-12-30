//
//  RNListView.swift
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 29/04/24.
//

import UIKit
import React

class RNListView: UIView {
    var delegate: TextProtocol?
    let resultModule = ResultModule()
    
    private var reactView: RCTRootView? = nil

    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        let bridge = getBridge()
        self.reactView = RCTRootView(bridge: bridge, moduleName: "SimpleList", initialProperties: [:] as [NSObject:AnyObject])
  
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
   
    }
    
    private func getBridge() -> RCTBridge{
        
//        let jsCodeLocation = URL(string: "http://localhost:8081/index.bundle?platform=ios")!
                let jsCodeLocation = Bundle.main.url(forResource: "main", withExtension: "jsbundle")!
    
        resultModule.delegate = self
        let bridge = RCTBridge(bundleURL: jsCodeLocation) {
            [self.resultModule] as? [RCTBridgeModule]
        }!
        
        
        return bridge
    }
    
    
    override func layoutSubviews() {
        guard let reactView = self.reactView else { return }
               addSubview(reactView)
       
               reactView.translatesAutoresizingMaskIntoConstraints = false
               NSLayoutConstraint.activate([
                   reactView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
                   reactView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
                   reactView.topAnchor.constraint(equalTo: self.topAnchor),
                   reactView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
               ])
    }
    
    
    func sendEvent(event: String){
        resultModule.sendEvent(event)
    }
}

extension RNListView: ResultModuleDelegate {
    func resultString(s: String) {
        guard let delegate = self.delegate else{ return }
        
        delegate.resultText(s: s)
    }
}
