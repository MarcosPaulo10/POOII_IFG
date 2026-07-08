import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Curso } from '../../models/curso';
import { CursoService } from '../../services/curso.service';

@Component({
  selector: 'app-curso-list',
  standalone: true,
  imports: [CommonModule, RouterModule, MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './curso-list.component.html',
  styleUrl: './curso-list.component.scss'
})
export class CursoListComponent implements OnInit {

  dataSource = new MatTableDataSource<Curso>([]);
  colunasExibidas: string[] = ['idCurso', 'nomeCurso', 'acoes'];

  constructor(private cursoService: CursoService) { }

  ngOnInit(): void {
    this.buscarCursos();
  }

  buscarCursos(): void {
    this.cursoService.findAll().subscribe({
      next: (dados) => this.dataSource.data = dados,
      error: (err) => console.error('Erro ao buscar cursos', err)
    });
  }

  excluir(id: number | undefined): void {
    if (!id) return;
    if (confirm('Deseja realmente excluir este curso?')) {
      this.cursoService.delete(id).subscribe({
        next: () => this.buscarCursos(),
        error: (err) => console.error('Erro ao excluir curso', err)
      });
    }
  }
}