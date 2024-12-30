//
//  MixedController.swift
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 29/04/24.
//

import UIKit

class MixedController: UIViewController {
    
    let lblNative = UILabel()
    let lblRN = UILabel()
    let nativeCardView = CardView()
    let rnCardView = CardView()
    let nativeList = UITableView()
    let rnList = RNListView()
    
    private var items = [
        "IOS Item 1",
        "IOS Item 2",
        "IOS Item 3",
        "IOS Item 4",
        "IOS Item 5",
    ]
    

    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.backgroundColor = .none
        view.backgroundColor = UIColor(cgColor: CGColor(red: 0.958, green: 0.958, blue: 0.958, alpha: 255))
        initLayout()
        // Do any additional setup after loading the view.
    }
    
    
    func initLayout(){
        view.addSubview(lblNative)
        view.addSubview(lblRN)
        view.addSubview(nativeCardView)
        view.addSubview(rnCardView)
        nativeCardView.addSubview(nativeList)
        rnCardView.addSubview(rnList)
       
        lblNative.font = lblNative.font.withSize(22)
        lblNative.text = "IOS List"
        lblNative.textColor = .gr1
        
        lblRN.font = lblRN.font.withSize(22)
        lblRN.text = "RN List"
        lblRN.textColor = .gr2
        
        let cardMargin: CGFloat = 10
        
        nativeList.dataSource = self
        nativeList.delegate = self
        
        rnList.delegate = self
       
        lblNative.translatesAutoresizingMaskIntoConstraints = false
        lblRN.translatesAutoresizingMaskIntoConstraints = false
        nativeCardView.translatesAutoresizingMaskIntoConstraints = false
        nativeList.translatesAutoresizingMaskIntoConstraints = false
        rnCardView.translatesAutoresizingMaskIntoConstraints = false
        rnList.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            lblNative.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 30),
            lblNative.topAnchor.constraint(equalTo: view.centerYAnchor, constant: 5),
            
            lblRN.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 30),

            nativeCardView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: cardMargin),
            nativeCardView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -cardMargin),
            nativeCardView.topAnchor.constraint(equalTo: lblNative.bottomAnchor, constant: 5),
            nativeCardView.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -cardMargin),
            
            rnCardView.leadingAnchor.constraint(equalTo: view.leadingAnchor,constant: cardMargin),
            rnCardView.trailingAnchor.constraint(equalTo: view.trailingAnchor,constant: -cardMargin),
            rnCardView.topAnchor.constraint(equalTo: lblRN.bottomAnchor,constant: 5),
            rnCardView.bottomAnchor.constraint(equalTo: lblNative.topAnchor, constant:-cardMargin),
            
            nativeList.leadingAnchor.constraint(equalTo: nativeCardView.leadingAnchor),
            nativeList.trailingAnchor.constraint(equalTo: nativeCardView.trailingAnchor),
            nativeList.topAnchor.constraint(equalTo: nativeCardView.topAnchor),
            nativeList.bottomAnchor.constraint(equalTo: nativeCardView.bottomAnchor),
            
            rnList.leadingAnchor.constraint(equalTo: rnCardView.leadingAnchor),
            rnList.trailingAnchor.constraint(equalTo: rnCardView.trailingAnchor),
            rnList.topAnchor.constraint(equalTo: rnCardView.topAnchor),
            rnList.bottomAnchor.constraint(equalTo: rnCardView.bottomAnchor)
        ])
        
        guard let navBar = navigationController?.navigationBar else { return }
        
        let position = navBar.layer.position.y + navBar.frame.height + 10
        lblRN.topAnchor.constraint(equalTo: view.topAnchor, constant: position).isActive = true
        
        
    }

}


extension MixedController: UITableViewDataSource, UITableViewDelegate, TextProtocol{

    // Data Source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let item = self.items[indexPath.row]
        let cell = UITableViewCell(style: .value1, reuseIdentifier: item)
        cell.textLabel?.font = UIFont.boldSystemFont(ofSize: 14)
        cell.textLabel?.text = item
        return cell
    }
    
    // Delegete
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let item = self.items[ indexPath.row]
       
        self.items.remove(at: indexPath.row)
        
        rnList.sendEvent(event: item)
        tableView.reloadData()
    }
    
    // TextProtocol
    func resultText(s: String) {
        items.append(s)
        DispatchQueue.main.async {
            self.nativeList.reloadData()
        }
    }

}
