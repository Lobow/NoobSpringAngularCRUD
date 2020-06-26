import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private http: Http) { }
  confirmationString:string = "New product has been added";
  isAdded: boolean = false;
  productObj:object = {};

  addNewProduct = function(product) {
    this.productObj = {
      "nome": product.nome,
      "valor": product.valor
    }
    this.http.post("http://localhost:8080/product", this.productObj).subscribe((res:Response) => {
      this.isAdded = true;
    })
  }

  ngOnInit() {
  }

}
