@extends('app')

@section('content')

{!! Form::open(array('url' => '/assets/equipment', 'class' => 'form-horizontal')) !!}
	
	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('Ecodelabel','Equipment Code: ') !!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('Ecode')!!}
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('enamelabel','Equipment Name: ') !!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('Ename')!!}
    	</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2"><b>Pre Existing Equipments</b></label>
		<div class="col-sm-10">
			<select id="selectmultiple" name="selectmultiple" class="form-control" multiple="multiple">
				@foreach($Equip as $index => $equipment)
      			<option value="{{$index+1}}">{{$equipment->Name}}</option>
      			@endforeach
			</select>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<input type="hidden" name="_token" value="{{ csrf_token() }}">
			<input type="submit" value="Submit">
		</div>
	</div>
{!! Form::close() !!}
@endsection