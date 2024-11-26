/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.structural.composite.model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Joao Pereira
 * 
 */
public abstract class CompositeShape extends Shape {

	List<Shape> shapes;

	public CompositeShape() {
		this.shapes = createShapesList();
	}

	/**
	 * Remove a shape from this shape childrens
	 * 
	 * @param shape
	 *            the shape to remove
	 * @return true if the shape was present and was removed, false if the shape
	 *         was not present
	 */
	public boolean removeShape(Shape shape) {
		// TODO: implement
		for(Shape shapeFromList: shapes) {
			if(shapeFromList.equals(shape)) {
				shapes.remove(shape);
				return true;
			}
		}
		return false;

	}

	/**
	 * Return the total shapes count in case this is a composite
	 * 
	 * @return the total count of shapes if the shape is composite. -1 otherwise
	 */
	public int getShapeCount() {
		// TODO: implement
		if(this.asComposite() != null) {
			return shapes.size();
		}
		return -1;
	}

	/**
	 * Add a shape to this shape.
	 * 
	 * @param shape
	 *            The shape to add
	 * @throws ShapeDoesNotSupportChildren
	 *             if this shape is not a composite
	 */
	public void addShape(Shape shape) {
		if(this.asComposite() != null) {
			shapes.add(shape);
		}
	}

	public List<Shape> getShapes() {
		// TODO: Implement
		if(this.asComposite() != null) {
			return this.shapes;
		}
		return null;

	}

	/**
	 * @param circle
	 * @return
	 */
	public List<Shape> getShapesByType(ShapeType circle) {
		if(this.asComposite() == null) {
			return null;
		}
		List<Shape> circleShapes = new ArrayList<>();
		for(Shape shape: shapes) {
			if(shape.getType() == circle) {
				circleShapes.add(shape);
			}
		}
		return circleShapes;
	}

	/**
	 * Return all shapes that are leafs in the tree
	 * 
	 * @return
	 */
	public List<Shape> getLeafShapes() {
		// TODO: Implement
		if(this.asComposite() == null) {
			return null;
		}
		List<Shape> leafShapes = new ArrayList<>();
		for(Shape shape: shapes) {
			if(shape instanceof LeafShape) {
				leafShapes.add(shape);
			}
		}
		return shapes;
	}

	/**
	 * Factory method for the list of children of this shape
	 * 
	 * @return
	 */
	protected List<Shape> createShapesList() {
		// TODO: Implement
		return new ArrayList<Shape>();
	}
}
