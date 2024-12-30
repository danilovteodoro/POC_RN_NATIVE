//
//  CardView.swift
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 29/04/24.
//

import UIKit

class CardView: UIView {
    
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        self.backgroundColor = .white
        self.layer.cornerRadius = 10
        self.layer.borderWidth = 0.1678
        
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
