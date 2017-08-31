//
//  ViewController.swift
//  Comprei!
//
//  Created by admin on 30/08/17.
//
//

import UIKit

class FormularioViewController: UIViewController {
    @IBOutlet weak var tfNome: UITextField!
    @IBOutlet weak var tfValor: UITextField!
    @IBOutlet weak var lbQuantidade: UILabel!
    @IBOutlet weak var stQuantidade: UIStepper!
    
    var compra: Compra!
    var item: Item?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.save, target: self, action: #selector(salvar))
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        
        if(item != nil) {
            tfNome.text = item?.nome
            tfValor.text = String(describing: item?.valor)
            lbQuantidade.text = String(describing: item?.qtde)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func definirQuantidade(_ sender: Any) {
        self.lbQuantidade.text = String(Int(self.stQuantidade.value))
    }
    
    
    func salvar() {
        let name = self.tfNome.text
        let valor = Float(self.tfValor.text!)
        let qtd = Int(self.stQuantidade.value)
        
        let item = Item(nome: name!, valor: valor!, qtde: qtd)
        
        self.compra.addItem(novo: item)
        self.navigationController?.popViewController(animated: true)
    }


}

