import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { CarService } from '../car.service';
import { Car } from '../models/car.model';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css'],
  standalone: true,
  imports: [CommonModule, HttpClientModule],
})

export class CarListComponent {
  cars: Car[] = [];

  constructor(private carService: CarService) {
    this.carService.getCars().subscribe((data) => {
      this.cars = data;
    });
  }
}

/*
export class CarListComponent implements OnInit {
  cars: Car[] = [];

  constructor(private carService: CarService) { }

  ngOnInit(): void {
    this.carService.getCars().subscribe(data => {
      this.cars = data;
    });
  }
}
*/
