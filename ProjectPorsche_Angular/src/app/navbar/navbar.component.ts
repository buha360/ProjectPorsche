import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule, 
    MatToolbarModule, 
    MatButtonModule, 
    MatIconModule, 
    MatSidenavModule, 
    MatListModule,
    RouterOutlet
  ],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  menuOpen = false;
  menuItems = [
    {
      title: 'Új és használt autók',
      subItems: ['Kínálatunk', 'Raktári járművek', 'Használt autók', 'Klasszikus autók', 'Porsche Certified Cars', 'Adja el autóját'],
      expanded: false
    },
    { title: 'Modellek', subItems: [], expanded: false },
    { title: 'Szerviz', subItems: [], expanded: false },
    { title: 'Alkatrészek', subItems: [], expanded: false },
    { title: 'Hírek', subItems: [], expanded: false },
    { title: 'Rólunk', subItems: [], expanded: false },
    { title: 'Porsche Shop', subItems: [], expanded: false },
    { title: 'Porsche kód megosztás', subItems: [], expanded: false }
  ];

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  toggleSubMenu(item: any) {
    if (item.subItems.length > 0) {
      item.expanded = !item.expanded;
    }
  }
}
