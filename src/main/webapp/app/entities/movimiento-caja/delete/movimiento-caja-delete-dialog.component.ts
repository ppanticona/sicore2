import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMovimientoCaja } from '../movimiento-caja.model';
import { MovimientoCajaService } from '../service/movimiento-caja.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './movimiento-caja-delete-dialog.component.html',
})
export class MovimientoCajaDeleteDialogComponent {
  movimientoCaja?: IMovimientoCaja;

  constructor(protected movimientoCajaService: MovimientoCajaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.movimientoCajaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
