/*
 * Copyright (c) 2010-2011 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.restfb.types;

import static com.restfb.util.DateUtils.toDateFromLongFormat;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.restfb.Facebook;

/**
 * Represents the <a
 * href="http://developers.facebook.com/docs/reference/api/photo">Photo Graph
 * API type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.5
 */
public class Photo extends NamedFacebookType {
  @Facebook
  private CategorizedFacebookType from;

  @Facebook
  private String picture;

  @Facebook
  private String source;

  @Facebook
  private Integer height;

  @Facebook
  private Integer width;

  @Facebook
  private String link;

  @Facebook
  private String icon;

  @Facebook("created_time")
  private String createdTime;

  @Facebook("updated_time")
  private String updatedTime;

  @Facebook
  private List<Tag> tags = new ArrayList<Tag>();

  /**
   * Represents the <a
   * href="http://developers.facebook.com/docs/reference/api/photo">Tag Graph
   * API type</a>.
   * 
   * @author <a href="http://restfb.com">Mark Allen</a>
   * @since 1.5
   */
  public static class Tag extends NamedFacebookType {
    @Facebook
    private Integer x;

    @Facebook
    private Integer y;

    @Facebook("created_time")
    private String createdTime;

    /**
     * X coordinate (as a percentage of distance from left vs. width).
     * 
     * @return X coordinate (as a percentage of distance from left vs. width).
     */
    public Integer getX() {
      return x;
    }

    /**
     * Y coordinate (as a percentage of distance from top vs. height).
     * 
     * @return Y coordinate (as a percentage of distance from top vs. height).
     */
    public Integer getY() {
      return y;
    }

    /**
     * Date this tag was created.
     * 
     * @return Date this tag was created.
     */
    public Date getCreatedTime() {
      return toDateFromLongFormat(createdTime);
    }
  }

  /**
   * An object containing the name and ID of the user who posted the photo.
   * 
   * @return An object containing the name and ID of the user who posted the
   *         photo.
   */
  public CategorizedFacebookType getFrom() {
    return from;
  }

  /**
   * The album-sized view of the photo.
   * 
   * @return The album-sized view of the photo.
   */
  public String getPicture() {
    return picture;
  }

  /**
   * The full-sized source of the photo.
   * 
   * @return The full-sized source of the photo.
   */
  public String getSource() {
    return source;
  }

  /**
   * The height of the photo, in pixels.
   * 
   * @return The height of the photo, in pixels.
   */
  public Integer getHeight() {
    return height;
  }

  /**
   * The width of the photo, in pixels.
   * 
   * @return The width of the photo, in pixels.
   */
  public Integer getWidth() {
    return width;
  }

  /**
   * A link to the photo on Facebook.
   * 
   * @return A link to the photo on Facebook.
   */
  public String getLink() {
    return link;
  }

  /**
   * The icon-sized source of the photo.
   * 
   * @return The icon-sized source of the photo.
   */
  public String getIcon() {
    return icon;
  }

  /**
   * The time the photo was initially published.
   * 
   * @return The time the photo was initially published.
   */
  public Date getCreatedTime() {
    return toDateFromLongFormat(createdTime);
  }

  /**
   * The last time the photo or its caption was updated.
   * 
   * @return The last time the photo or its caption was updated.
   */
  public Date getUpdatedTime() {
    return toDateFromLongFormat(updatedTime);
  }

  /**
   * An array containing the users and their positions in this photo. The x and
   * y coordinates are percentages from the left and top edges of the photo,
   * respectively.
   * 
   * @return An array containing the users and their positions in this photo.
   *         The x and y coordinates are percentages from the left and top edges
   *         of the photo, respectively.
   */
  public List<Tag> getTags() {
    return unmodifiableList(tags);
  }
}