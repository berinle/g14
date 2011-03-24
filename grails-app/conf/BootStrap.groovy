import com.demo.Owner
import org.hibernate.search.Search

class BootStrap {

    def init = { servletContext ->
	
		new Owner(name:"Bayo").save()
		new Owner(name:"Seun").save()
		new Owner(name:"Segun").save()
		
		println "created 3 owners..."
		
		Owner.withSession { session -> 
			def fts = Search.getFullTextSession(session)
			fts.beginTransaction()
			
			/*fts.createIndexer(Owner.class)
						.batchSizeToLoadObjects(30)
						            .threadsForSubsequentFetching(8)
						            .threadsToLoadObjects(4)
						            .startAndWait()*/
						
			Owner.list().each {
				fts.index(it)
			}
			fts.flushToIndexes()
			
			fts.getTransaction().commit()
			fts.close()
			
			log.info 'Did something in withSession{} block'
		}
		
		log.info 'Done building initial index!'
		
    }
    def destroy = {
    }
}
