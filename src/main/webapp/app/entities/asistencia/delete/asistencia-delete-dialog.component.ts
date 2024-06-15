import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAsistencia } from '../asistencia.model';
import { AsistenciaService } from '../service/asistencia.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './asistencia-delete-dialog.component.html',
})
export class AsistenciaDeleteDialogComponent {
  asistencia?: IAsistencia;

  constructor(protected asistenciaService: AsistenciaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.asistenciaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
