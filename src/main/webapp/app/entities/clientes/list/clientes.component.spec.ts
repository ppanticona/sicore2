import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ClientesService } from '../service/clientes.service';

import { ClientesComponent } from './clientes.component';

describe('Clientes Management Component', () => {
  let comp: ClientesComponent;
  let fixture: ComponentFixture<ClientesComponent>;
  let service: ClientesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'clientes', component: ClientesComponent }]), HttpClientTestingModule],
      declarations: [ClientesComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(ClientesComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ClientesComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ClientesService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.clientes?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to clientesService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getClientesIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getClientesIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
