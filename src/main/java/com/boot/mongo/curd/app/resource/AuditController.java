package com.boot.mongo.curd.app.resource;

import java.util.ArrayList;
import java.util.List;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.mongo.curd.app.model.Book;

@RestController
@RequestMapping("/aduit")
public class AuditController {

	private final Javers javers;

	public AuditController(Javers javers) {
		this.javers = javers;
	}

	@GetMapping("/book")
	public ResponseEntity<String> getBookInfoChanges() {
		QueryBuilder javersQueryBuilder = QueryBuilder.byClass(Book.class);
		List<Change> changes = javers.findChanges(javersQueryBuilder.build());
		return ResponseEntity.ok().body(javers.getJsonConverter().toJson(changes));
	}

	@GetMapping("/books")
	public String getBookInforChanges2() {
		QueryBuilder jqb = QueryBuilder.byClass(Book.class).withNewObjectChanges();
		Changes change = javers.findChanges(jqb.build());
		return "<pre>" + change.prettyPrint() + "</pre>";
	}

	@GetMapping("/book/{id}")
	public String getBookInfoChangesById(@PathVariable String id) {
		QueryBuilder jBuilder = QueryBuilder.byInstanceId(id, Book.class).withNewObjectChanges();
		Changes changes = javers.findChanges(jBuilder.build());
		return "<pre>" + changes.prettyPrint() + "</pre>";
	}

	@GetMapping("/book/snapshots")
	public String getBookSnapshots() {
		QueryBuilder queryBuilder = QueryBuilder.byClass(Book.class);
		List<CdoSnapshot> changes = new ArrayList(javers.findSnapshots(queryBuilder.build()));
		changes.sort((o1, o2) -> -1
				* o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));
		JsonConverter jsonConverter = javers.getJsonConverter();

		return jsonConverter.toJson(changes);
	}

	@GetMapping("/book/{login}/snapshots")
	public String getBookInfoSnapshots(@PathVariable String login) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(login, Book.class);

		List<CdoSnapshot> changes = javers.findSnapshots(jqlQuery.build());

		changes.sort((o1, o2) -> -1
				* o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

		JsonConverter jsonConverter = javers.getJsonConverter();

		return jsonConverter.toJson(changes);
	}

}
