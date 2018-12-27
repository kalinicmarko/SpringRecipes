INSERT INTO recipe (name, description, prop_time, cook_time, difficulty, note_id) VALUES ('Spaghetti', 'Spaghetti Sauce with Ground Beef', 10, 20, 'EASY', 1);
INSERT INTO recipe (name, description, prop_time, cook_time, difficulty, note_id) VALUES ('Lasagna', 'The most amazing lasagna recipe', 20, 30, 'MODERATE', 1);
INSERT INTO recipe (name, description, prop_time, cook_time, difficulty, note_id) VALUES ('Chicken', 'Oregano chicken & squash traybake', 20, 30, 'MODERATE', 1);
INSERT INTO note (description) VALUES ('Lorem');
INSERT INTO category (name) VALUES ('American');
INSERT INTO category (name) VALUES ('Mexican');
INSERT INTO category (name) VALUES ('Italian');
--INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 3);
--INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 2);
--INSERT INTO recipe_category (recipe_id, category_id) VALUES (3, 1);
--INSERT INTO recipe_category (recipe_id, category_id) VALUES (3, 2);
INSERT INTO ingredient (name, amount, recipe_id, uom_id) VALUES ('Olive oil', 2.00, 3, 3);
INSERT INTO ingredient (name, amount, recipe_id, uom_id) VALUES ('Salt', 0.50, 2, 2);
INSERT INTO ingredient (name, amount, recipe_id, uom_id) VALUES ('fresh lime juice or lemon juice', 2.00, 3, 3);
INSERT INTO ingredient (name, amount, recipe_id, uom_id) VALUES ('ripe avocados', 0.50, 2, 4);
INSERT INTO ingredient (name, amount, recipe_id, uom_id) VALUES ('red onion, thinly sliced', 0.25, 1, 4);
INSERT INTO unit_of_measure (name) VALUES ('tablespoon');
INSERT INTO unit_of_measure (name) VALUES ('teaspoon');
INSERT INTO unit_of_measure (name) VALUES ('cup');
INSERT INTO unit_of_measure (name) VALUES ('kg');