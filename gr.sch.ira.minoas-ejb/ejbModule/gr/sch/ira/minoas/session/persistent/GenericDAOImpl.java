/*
 * 
 *
 * Copyright (c) 2007 FORTHnet, S.A. All Rights Reserved.
 *
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package gr.sch.ira.minoas.session.persistent;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;

/**
 * @author <a href="mailto:fsla@forthnet.gr">Filippos Slavik</a>
 * @version $Id$
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	protected Collection<T> EMPTY_COLLECTION;

	@In
	protected EntityManager entityManager;

	private Class<T> persistentClass;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected GenericDAOImpl() {
		super();
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * @param persistentClass
	 */
	protected GenericDAOImpl(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	/**
	 * @see gr.forthnet.openseas.persistent.dao.IGenericDAO#findByID(java.io.Serializable)
	 */
	public T findByID(ID id) {
		return getEntityManager().find(persistentClass, id);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @see gr.sch.ira.minoas.session.persistent.IGenericDAO#persist(java.lang.Object)
	 */
	public T persist(T entityInstance) {
		getEntityManager().persist(entityInstance);
		return entityInstance;
	}

}
