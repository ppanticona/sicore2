import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IRegCompras } from '../reg-compras.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../reg-compras.test-samples';

import { RegComprasService, RestRegCompras } from './reg-compras.service';

const requireRestSample: RestRegCompras = {
  ...sampleWithRequiredData,
  fecEmiComp: sampleWithRequiredData.fecEmiComp?.toJSON(),
  fecVencComp: sampleWithRequiredData.fecVencComp?.toJSON(),
  fecEmiModif: sampleWithRequiredData.fecEmiModif?.toJSON(),
  fecEmiDetracc: sampleWithRequiredData.fecEmiDetracc?.toJSON(),
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('RegCompras Service', () => {
  let service: RegComprasService;
  let httpMock: HttpTestingController;
  let expectedResult: IRegCompras | IRegCompras[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RegComprasService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a RegCompras', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const regCompras = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(regCompras).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RegCompras', () => {
      const regCompras = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(regCompras).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RegCompras', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RegCompras', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RegCompras', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRegComprasToCollectionIfMissing', () => {
      it('should add a RegCompras to an empty array', () => {
        const regCompras: IRegCompras = sampleWithRequiredData;
        expectedResult = service.addRegComprasToCollectionIfMissing([], regCompras);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(regCompras);
      });

      it('should not add a RegCompras to an array that contains it', () => {
        const regCompras: IRegCompras = sampleWithRequiredData;
        const regComprasCollection: IRegCompras[] = [
          {
            ...regCompras,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRegComprasToCollectionIfMissing(regComprasCollection, regCompras);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RegCompras to an array that doesn't contain it", () => {
        const regCompras: IRegCompras = sampleWithRequiredData;
        const regComprasCollection: IRegCompras[] = [sampleWithPartialData];
        expectedResult = service.addRegComprasToCollectionIfMissing(regComprasCollection, regCompras);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(regCompras);
      });

      it('should add only unique RegCompras to an array', () => {
        const regComprasArray: IRegCompras[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const regComprasCollection: IRegCompras[] = [sampleWithRequiredData];
        expectedResult = service.addRegComprasToCollectionIfMissing(regComprasCollection, ...regComprasArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const regCompras: IRegCompras = sampleWithRequiredData;
        const regCompras2: IRegCompras = sampleWithPartialData;
        expectedResult = service.addRegComprasToCollectionIfMissing([], regCompras, regCompras2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(regCompras);
        expect(expectedResult).toContain(regCompras2);
      });

      it('should accept null and undefined values', () => {
        const regCompras: IRegCompras = sampleWithRequiredData;
        expectedResult = service.addRegComprasToCollectionIfMissing([], null, regCompras, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(regCompras);
      });

      it('should return initial array if no RegCompras is added', () => {
        const regComprasCollection: IRegCompras[] = [sampleWithRequiredData];
        expectedResult = service.addRegComprasToCollectionIfMissing(regComprasCollection, undefined, null);
        expect(expectedResult).toEqual(regComprasCollection);
      });
    });

    describe('compareRegCompras', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRegCompras(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareRegCompras(entity1, entity2);
        const compareResult2 = service.compareRegCompras(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareRegCompras(entity1, entity2);
        const compareResult2 = service.compareRegCompras(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareRegCompras(entity1, entity2);
        const compareResult2 = service.compareRegCompras(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
