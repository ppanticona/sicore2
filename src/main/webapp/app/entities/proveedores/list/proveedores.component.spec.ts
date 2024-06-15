import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ProveedoresService } from '../service/proveedores.service';

import { ProveedoresComponent } from './proveedores.component';

describe('Proveedores Management Component', () => {
  let comp: ProveedoresComponent;
  let fixture: ComponentFixture<ProveedoresComponent>;
  let service: ProveedoresService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'proveedores', component: ProveedoresComponent }]), HttpClientTestingModule],
      declarations: [ProveedoresComponent],
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
      .overrideTemplate(ProveedoresComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProveedoresComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ProveedoresService);

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
    expect(comp.proveedores?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to proveedoresService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getProveedoresIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getProveedoresIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
