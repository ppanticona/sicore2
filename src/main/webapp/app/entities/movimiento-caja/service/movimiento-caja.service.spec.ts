import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IMovimientoCaja } from '../movimiento-caja.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../movimiento-caja.test-samples';

import { MovimientoCajaService, RestMovimientoCaja } from './movimiento-caja.service';

const requireRestSample: RestMovimientoCaja = {
  ...sampleWithRequiredData,
  fecMovimiento: sampleWithRequiredData.fecMovimiento?.toJSON(),
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('MovimientoCaja Service', () => {
  let service: MovimientoCajaService;
  let httpMock: HttpTestingController;
  let expectedResult: IMovimientoCaja | IMovimientoCaja[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(MovimientoCajaService);
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

    it('should create a MovimientoCaja', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const movimientoCaja = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(movimientoCaja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a MovimientoCaja', () => {
      const movimientoCaja = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(movimientoCaja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a MovimientoCaja', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of MovimientoCaja', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a MovimientoCaja', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMovimientoCajaToCollectionIfMissing', () => {
      it('should add a MovimientoCaja to an empty array', () => {
        const movimientoCaja: IMovimientoCaja = sampleWithRequiredData;
        expectedResult = service.addMovimientoCajaToCollectionIfMissing([], movimientoCaja);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(movimientoCaja);
      });

      it('should not add a MovimientoCaja to an array that contains it', () => {
        const movimientoCaja: IMovimientoCaja = sampleWithRequiredData;
        const movimientoCajaCollection: IMovimientoCaja[] = [
          {
            ...movimientoCaja,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMovimientoCajaToCollectionIfMissing(movimientoCajaCollection, movimientoCaja);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a MovimientoCaja to an array that doesn't contain it", () => {
        const movimientoCaja: IMovimientoCaja = sampleWithRequiredData;
        const movimientoCajaCollection: IMovimientoCaja[] = [sampleWithPartialData];
        expectedResult = service.addMovimientoCajaToCollectionIfMissing(movimientoCajaCollection, movimientoCaja);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(movimientoCaja);
      });

      it('should add only unique MovimientoCaja to an array', () => {
        const movimientoCajaArray: IMovimientoCaja[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const movimientoCajaCollection: IMovimientoCaja[] = [sampleWithRequiredData];
        expectedResult = service.addMovimientoCajaToCollectionIfMissing(movimientoCajaCollection, ...movimientoCajaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const movimientoCaja: IMovimientoCaja = sampleWithRequiredData;
        const movimientoCaja2: IMovimientoCaja = sampleWithPartialData;
        expectedResult = service.addMovimientoCajaToCollectionIfMissing([], movimientoCaja, movimientoCaja2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(movimientoCaja);
        expect(expectedResult).toContain(movimientoCaja2);
      });

      it('should accept null and undefined values', () => {
        const movimientoCaja: IMovimientoCaja = sampleWithRequiredData;
        expectedResult = service.addMovimientoCajaToCollectionIfMissing([], null, movimientoCaja, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(movimientoCaja);
      });

      it('should return initial array if no MovimientoCaja is added', () => {
        const movimientoCajaCollection: IMovimientoCaja[] = [sampleWithRequiredData];
        expectedResult = service.addMovimientoCajaToCollectionIfMissing(movimientoCajaCollection, undefined, null);
        expect(expectedResult).toEqual(movimientoCajaCollection);
      });
    });

    describe('compareMovimientoCaja', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMovimientoCaja(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareMovimientoCaja(entity1, entity2);
        const compareResult2 = service.compareMovimientoCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareMovimientoCaja(entity1, entity2);
        const compareResult2 = service.compareMovimientoCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareMovimientoCaja(entity1, entity2);
        const compareResult2 = service.compareMovimientoCaja(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
