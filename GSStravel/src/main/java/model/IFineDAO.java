package model;

import java.util.List;

public interface IFineDAO {
	public abstract FineVO select(int day);

	public abstract List<FineVO> select();

	public abstract FineVO insert(FineVO bean);

//	public abstract FineVO update(float percent, int day);

//	public abstract boolean delete(int day);
	
	public abstract void delete(int day);
}
