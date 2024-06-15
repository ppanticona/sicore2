import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAsignacionCaja } from '../asignacion-caja.model';
import { AsignacionCajaService } from '../service/asignacion-caja.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './asignacion-caja-delete-dialog.component.html',
})
export class AsignacionCajaDeleteDialogComponent {
  asignacionCaja?: IAsignacionCaja;

  constructor(protected asignacionCajaService: AsignacionCajaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.asignacionCajaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
