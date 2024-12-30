//
//  SecondScreen.swift
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 16/04/24.
//

import UIKit

protocol TextProtocol {
    func resultText(s: String)
}

class SecondScreen: UIViewController {
    
    var delegate:TextProtocol?
    
    let textField = UITextField()
    let button = UIButton()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .systemBackground
        setupComponents()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = true
    }
    
    func setupComponents(){
        // add views
        view.addSubview(textField)
        view.addSubview(button)
        
        // configure views
        textField.placeholder = "write something here"
        let space: CGFloat = 5
        textField.borderStyle = .roundedRect
        
        button.configuration = .filled()
        button.configuration?.title = "Ok"
        button.addTarget(self, action: #selector(resulText), for: .touchUpInside)
    
        textField.translatesAutoresizingMaskIntoConstraints = false
        button.translatesAutoresizingMaskIntoConstraints = false
        
        // configure layout constraints
        NSLayoutConstraint.activate([
//            textField.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            textField.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            textField.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: space),
            textField.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -5),
            
            button.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            button.topAnchor.constraint(equalTo: textField.bottomAnchor, constant: 10)
        ])
    }
    
    @objc func resulText() {
        guard let text = textField.text else {
            return
        }
//        
        guard let delegate = self.delegate else {
            return
        }
        
        
        delegate.resultText(s: text)
        navigationController?.popViewController(animated: true)
    }
}
