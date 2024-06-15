import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { SecuenciasService } from '../service/secuencias.service';

import { SecuenciasComponent } from './secuencias.component';

describe('Secuencias Management Component', () => {
  let comp: SecuenciasComponent;
  let fixture: ComponentFixture<SecuenciasComponent>;
  let service: SecuenciasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'secuencias', component: SecuenciasComponent }]), HttpClientTestingModule],
      declarations: [SecuenciasComponent],
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
      .overrideTemplate(SecuenciasComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SecuenciasComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(SecuenciasService);

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
    expect(comp.secuencias?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to secuenciasService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getSecuenciasIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getSecuenciasIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
