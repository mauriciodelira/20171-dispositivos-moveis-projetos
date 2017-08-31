//
//  ListarItensTableViewController.swift
//  Comprei!
//
//  Created by admin on 30/08/17.
//
//

import UIKit

class ListarItensTableViewCell: UITableViewCell {
    @IBOutlet weak var lbNomeItem: UILabel!
    @IBOutlet weak var lbQtdItens: UILabel!
    @IBOutlet weak var btCheck: UIButton!
    
}

class ListarItensTableViewController: UITableViewController {

    var compra: Compra!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        self.tableView.reloadData()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return compra.size()
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "celula", for: indexPath) as! ListarItensTableViewCell

        let item = self.compra.get(pos: indexPath.row)
        
        cell.lbNomeItem.text = item.nome
        
        if(item.qtde != 1) {
            cell.lbQtdItens.text = "\(item.qtde) itens"
        } else {
            cell.lbQtdItens.text = "\(item.qtde) item"
        }
        
        
        // coloca um checkmark ao lado se estiver setado um boolean em item
        if(item.isComprado()) {
            cell.btCheck.setImage(#imageLiteral(resourceName: "checked-checkbox-filled.png"), for: UIControlState.normal)
        } else {
            cell.btCheck.setImage(#imageLiteral(resourceName: "unchecked-checkbox-filled.png"), for: UIControlState.normal)
        }
        
        
        return cell
    }
    
    @IBAction func toqueCheckBox(_ sender: Any) {
        
        let buttonPosition = (sender as AnyObject).convert(CGPoint(), to: tableView) // pega a posição do toque dentro do CGPoint
        let indexPath = tableView.indexPathForRow(at: buttonPosition) // puxa o indexPath segundo o ponto XY tirado acima
        
        if ((indexPath) != nil) {
            let item = self.compra.get(pos: (indexPath?.row)!)
            if(item.isComprado()) {
                print("checkbox: item comprado era true, agora false")
                item.comprado = false
            } else {
                print("checkbox: item comprado era false, agora true")
                item.comprado = true
            }
            tableView.reloadData()
        }
        
    }

    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }


    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            compra.delItem(pos: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    

    
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        compra.moveItem(origem: fromIndexPath.row, destino: to.row)
    }
    

    
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        if(segue.identifier == "itens_detalhe") {
            let itemDetalhe = segue.destination as! FormularioViewController
            itemDetalhe.compra = self.compra
            
            if let indexPath = tableView.indexPathForSelectedRow{
                print("row selecionada: \(indexPath.row)")
                itemDetalhe.item = self.compra.get(pos: indexPath.row)
            }
            
        }
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        
    
    }
    

}
