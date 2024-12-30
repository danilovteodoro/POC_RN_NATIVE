//
//  ViewController.swift
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 16/04/24.
//

import UIKit
import React

class FirstScreen: UIViewController, TextProtocol {
   
    let nextButton = UIButton()
    let textField = UITextField()
    let rnLabel = UILabel()
    let returnedLabel = UILabel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupLayout()
        view.backgroundColor = .systemBackground
        title = "Native Screen"
//        navigationController?.navigationBar.prefersLargeTitles = true
    }

    func setupLayout(){
        view.addSubview(nextButton)
        view.addSubview(textField)
        view.addSubview(rnLabel)
        view.addSubview(returnedLabel)
        
        nextButton.configuration = .filled()
        nextButton.configuration?.baseBackgroundColor = .systemPink
        nextButton.configuration?.title = "Send to RN"
        nextButton.addTarget(self, action: #selector(addReactView), for: .touchUpInside)
        
        textField.borderStyle = .roundedRect
        
        rnLabel.font = rnLabel.font.withSize(23)
        rnLabel.text = "Returned by RN app:"
        
        returnedLabel.font = UIFont.boldSystemFont(ofSize: 25)
        returnedLabel.text = "Returned Text"
        
        resultVisibility(visible: false)
        
        nextButton.translatesAutoresizingMaskIntoConstraints = false
        textField.translatesAutoresizingMaskIntoConstraints = false
        rnLabel.translatesAutoresizingMaskIntoConstraints = false
        returnedLabel.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            nextButton.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            nextButton.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            nextButton.widthAnchor.constraint(equalToConstant: 200),
            nextButton.heightAnchor.constraint(equalToConstant: 50),
            
            textField.bottomAnchor.constraint(equalTo: nextButton.topAnchor, constant: -10),
            textField.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 5),
            textField.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -5),
            
            rnLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
            rnLabel.topAnchor.constraint(equalTo: view.topAnchor, constant: 150),
            returnedLabel.topAnchor.constraint(equalTo: rnLabel.bottomAnchor, constant: 10),
            returnedLabel.leadingAnchor.constraint(equalTo: rnLabel.leadingAnchor,constant: 20)
        ])
        
      
    }
    
    
    func resultVisibility(visible: Bool) {
        rnLabel.isHidden = !visible
        returnedLabel.isHidden = !visible
    }
    
    func resultText(s: String) {
        print("LOG message: \(s)")
        returnedLabel.text = s
        resultVisibility(visible: true)
    }
    
    @objc func addReactView() {
        guard let text = textField.text else {
            return
        }
        
        let rnController = RNViewController()
        rnController.initialProps = ["name":text]
        rnController.modalPresentationStyle = .fullScreen
        rnController.delegate = self
        self.present(rnController, animated: true)
        textField.text = ""
    }
}

